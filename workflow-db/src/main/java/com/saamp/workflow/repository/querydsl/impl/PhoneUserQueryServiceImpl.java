package com.saamp.workflow.repository.querydsl.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saamp.workflow.entity.PhoneUserEntity;
import com.saamp.workflow.entity.QPhoneUserEntity;
import com.saamp.workflow.repository.PhoneUserRepository;
import com.saamp.workflow.repository.querydsl.PhoneUserQueryService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhoneUserQueryServiceImpl implements PhoneUserQueryService {

    private final EntityManager entityManager;

    private final PhoneUserRepository phoneUserRepository;

    @Override
    public PhoneUserEntity searchDetailUser(UUID id){
        return phoneUserRepository.findById(id).orElse(null);
    }

    @Override
    public Page<PhoneUserEntity> searchWithPredicate(String q, PageRequest pr, Long mysaampIdClient) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        QPhoneUserEntity user = QPhoneUserEntity.phoneUserEntity;

        String term = (q == null) ? null : q.trim();

        BooleanBuilder predicate = new BooleanBuilder();

        if(mysaampIdClient != null){
            predicate.and(user.mysaampIdClient.eq(mysaampIdClient));
        }

        predicate.and(user.company.notEqualsIgnoreCase("saamp"));
        if (term != null && !term.isBlank()) {
            predicate.and(
                    user.firstname.containsIgnoreCase(term)
                            .or(user.lastname.containsIgnoreCase(term))
                            .or(user.denomination.containsIgnoreCase(term))
                            .or(user.company.containsIgnoreCase(term))
                            .or(user.phoneOther.phoneNumber.containsIgnoreCase(term))
                            .or(user.phonePro.phoneNumber.containsIgnoreCase(term))
                            .or(user.phonePerso.phoneNumber.containsIgnoreCase(term))
                            .or(user.phoneTelavox.phoneNumber.containsIgnoreCase(term))
            );
        }

        // Requête principale avec LEFT JOIN explicites (c'est ça qui débloque ton cas)
        var base = queryFactory
                .selectDistinct(user)
                .from(user)
                .leftJoin(user.phonePro)
                .leftJoin(user.phonePerso)
                .leftJoin(user.phoneOther)
                .leftJoin(user.phoneTelavox)
                .where(predicate);

        // Appliquer le tri du pageable si présent
        applySort(base, pr.getSort(), user);

        List<PhoneUserEntity> content = base
                .offset(pr.getOffset())
                .limit(pr.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(user.id.countDistinct())
                .from(user)
                .leftJoin(user.phonePro)
                .leftJoin(user.phonePerso)
                .leftJoin(user.phoneOther)
                .leftJoin(user.phoneTelavox)
                .where(predicate)
                .fetchOne();

        return new PageImpl<>(content, pr, total == null ? 0L : total);
    }

    private void applySort(com.querydsl.jpa.impl.JPAQuery<PhoneUserEntity> query, Sort sort, QPhoneUserEntity user) {
        if (sort == null || sort.isUnsorted()) return;

        PathBuilder<PhoneUserEntity> entityPath =
                new PathBuilder<>(PhoneUserEntity.class, user.getMetadata().getName());

        for (Sort.Order o : sort) {
            Order direction = o.isAscending() ? Order.ASC : Order.DESC;

            Expression<? extends Comparable> sortExpr =
                    entityPath.getComparable(o.getProperty(), Comparable.class);

            query.orderBy(new OrderSpecifier<>(direction, sortExpr));
        }
    }
}

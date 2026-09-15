package com.saamp.workflow.service;

import com.saamp.olddb.entity.OldContact;
import com.saamp.olddb.repository.OldContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OldContactLookupService {

    private final OldContactRepository oldContactRepository;

    /**
     * Recherche une identité dans l'ancienne base à partir d'un numéro :
     * 1) dans la table contact (avec join client) via searchByAnyPhone
     * 2) si rien trouvé, fallback sur la table client via searchByAnyPhoneInClTable
     */
    @Transactional(readOnly = true, transactionManager = "oldDbTransactionManager")
    public Optional<OldContactRepository.ContactView> searchByAnyPhone(String normalizedPhone) {

        // Source 1 : table contact (join client)
        Optional<OldContactRepository.ContactView> fromContact = firstOf(oldContactRepository.searchByAnyPhone(normalizedPhone));
        if (fromContact.isPresent()) {
            return fromContact;
        }

        // Source 2 : table client uniquement
        return firstOf(oldContactRepository.searchByAnyPhoneInClTable(normalizedPhone));
    }

    private Optional<OldContactRepository.ContactView> firstOf(List<OldContactRepository.ContactView> matches) {
        if (matches == null || matches.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(matches.getFirst());
    }
}

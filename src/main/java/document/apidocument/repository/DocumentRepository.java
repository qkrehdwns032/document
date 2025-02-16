package document.apidocument.repository;

import document.apidocument.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    Optional<Document> findFirstByOrderByDateDesc();

    // 프로젝트 ID로 문서 조회 (날짜 내림차순)
    List<Document> findByProject_IdOrderByDateDesc(Long projectId);
}

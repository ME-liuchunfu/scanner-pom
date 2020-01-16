package xin.spring.javafx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xin.spring.javafx.domain.FileTable;

@Repository
public interface FileRepository extends JpaRepository<FileTable, Long> {

}

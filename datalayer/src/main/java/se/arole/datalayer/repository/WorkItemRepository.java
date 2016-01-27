package se.arole.datalayer.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import se.arole.datalayer.entity.UserJPA;
import se.arole.datalayer.entity.WorkItemJPA;

public interface WorkItemRepository extends PagingAndSortingRepository<WorkItemJPA, Long>{

List<WorkItemJPA> findBySolver(UserJPA solver);
List<WorkItemJPA> findByStatus(String status);
WorkItemJPA findByItemId(Integer itemId);

}

package com.loopy.domain.parkingrecord.recordCache;


import org.springframework.data.repository.CrudRepository;

public interface RecordCacheRepository extends CrudRepository<RecordCache, Long> {

    RecordCache findByParkinglotId (Long parkinglotId);
}
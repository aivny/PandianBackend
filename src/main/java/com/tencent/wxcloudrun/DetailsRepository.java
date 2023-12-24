package com.tencent.wxcloudrun;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DetailsRepository extends CrudRepository<DetailsEntity, String> {
    List<DetailsEntity>findDetailsEntitiesByNoContains(String no);
}

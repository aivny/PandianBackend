package com.tencent.wxcloudrun;

import org.springframework.data.repository.CrudRepository;

public interface BrandsRepository extends CrudRepository<BrandsEntity, String> {
    public Iterable<BrandsEntity> findByType(String type);
}

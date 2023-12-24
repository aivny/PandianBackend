package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.*;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.CounterRequest;
import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.service.CounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.alibaba.fastjson2.JSONObject;
/**
 * counter控制器
 */
@RestController

public class CounterController {

  @Autowired
  BrandsRepository brandsRepository;
  @Autowired
  TypesRepository typesRepository;
  @Autowired
  AreasRepository areasRepository;
  @Autowired
  UsersRepository usersRepository;
  @Autowired
  DetailsRepository detailsRepository;

  final CounterService counterService;
  final Logger logger;

  public CounterController(@Autowired CounterService counterService) {
    this.counterService = counterService;
    this.logger = LoggerFactory.getLogger(CounterController.class);
  }


  /**
   * 获取当前计数
   * @return API response json
   */
  @GetMapping(value = "/api/count")
  ApiResponse get() {
    logger.info("/api/count get request");
    Optional<Counter> counter = counterService.getCounter(1);
    Integer count = 0;
    if (counter.isPresent()) {
      count = counter.get().getCount();
    }

    return ApiResponse.ok(count);
  }

  @GetMapping(value = "/test")
  ApiResponse test(){
    return ApiResponse.ok("In service.");
  }

  @RequestMapping("getType")
  @CrossOrigin(origins = "*")
  @ResponseBody
  public Iterable<TypesEntity> getType(){

//        Map<String, Iterable<TypesEntity>> map = new HashMap<>();
//        map.put("data",typesRepository.findAll());

    return typesRepository.findAll();

  }

  @RequestMapping("getBrand")
  @CrossOrigin(origins = "*")
  @ResponseBody
  public Iterable<BrandsEntity> getBrand(@RequestBody String type){

//        Map<String, Iterable<BrandsEntity>> map = new HashMap<>();
//        map.put("data",brandsRepository.findByType(type));

    return brandsRepository.findByType(type);

  }

  @RequestMapping("getArea")
  @CrossOrigin(origins = "*")
  @ResponseBody
  public Map<String, Iterable<AreasEntity>> getArea(){

    Map<String, Iterable<AreasEntity>> map = new HashMap<>();
    map.put("data",areasRepository.findAll());

    return map;

  }

  @PostMapping("query")
  @CrossOrigin(origins = "*")
  @ResponseBody
  public Iterable<DetailsEntity> query(@RequestBody String id){
    return detailsRepository.findDetailsEntitiesByNoContains(id);
  }

  @PostMapping("delete")
  @CrossOrigin("*")
  @ResponseBody
  public Object delete(@RequestBody String id){
    try{
      detailsRepository.deleteById(id);
    } catch(Exception e){
      return e.toString();
    }
    return "success";
  }

  @PostMapping("login")
  @CrossOrigin(origins = "*")
  @ResponseBody
  public Object login(@RequestBody String req){
    JSONObject jsonObject = JSONObject.parse(req);
    String username = jsonObject.getString("username");
    String password = jsonObject.getString("password");

    JSONObject res = new JSONObject();
    if (usersRepository.existsByUsernameAndPassword(username,password)){
      res.put("result","ok");
      res.put("uniqId","qwer1234");
      return res;
    } else {
      res.put("result","notok");
      res.put("msg","登录失败");
      return res;
    }

  }

  @PostMapping("/submit")
  @CrossOrigin(origins = "*")
  @ResponseBody
  public Object submit(@RequestBody String req){
    JSONObject jsonObject = JSONObject.parse(req);
    DetailsEntity detailsEntity = new DetailsEntity();

    try{
      detailsEntity.setNo(jsonObject.getString("no"));
      detailsEntity.setBrand(jsonObject.getString("brand"));
      detailsEntity.setType(jsonObject.getString("type"));
      detailsEntity.setArea(jsonObject.getString("area"));
      detailsEntity.setModel(jsonObject.getString("model"));
      detailsEntity.setSeat(jsonObject.getString("seat"));

      detailsRepository.save(detailsEntity);
    } catch (Exception e){
      return e.toString();
    }

    return "success";
  }
  /**
   * 更新计数，自增或者清零
   * @param request {@link CounterRequest}
   * @return API response json
   */
  @PostMapping(value = "/api/count")
  ApiResponse create(@RequestBody CounterRequest request) {
    logger.info("/api/count post request, action: {}", request.getAction());

    Optional<Counter> curCounter = counterService.getCounter(1);
    if (request.getAction().equals("inc")) {
      Integer count = 1;
      if (curCounter.isPresent()) {
        count += curCounter.get().getCount();
      }
      Counter counter = new Counter();
      counter.setId(1);
      counter.setCount(count);
      counterService.upsertCount(counter);
      return ApiResponse.ok(count);
    } else if (request.getAction().equals("clear")) {
      if (!curCounter.isPresent()) {
        return ApiResponse.ok(0);
      }
      counterService.clearCount(1);
      return ApiResponse.ok(0);
    } else {
      return ApiResponse.error("参数action错误");
    }
  }
  
}
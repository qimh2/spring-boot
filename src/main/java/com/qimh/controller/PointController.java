package com.qimh.controller;

import com.qimh.aspect.PointRule;
import com.qimh.dto.response.RestResp;
import com.qimh.entitys.Point;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("point")
public class PointController {

    @PostMapping("/getPoint")
    @PointRule
    public RestResp<Point> getPoint(@RequestBody Point point){
        return new RestResp<>(point);
    }
    @PostMapping("/getPoint2")
    @PointRule
    public RestResp<Point> getPoint2(@RequestBody Point point){

        return new RestResp<>(point);
    }
    @PostMapping("/getPoint3")
    @PointRule
    public RestResp<Point> getPoint3(@RequestBody Point point){

        return new RestResp<>(point);
    }


}

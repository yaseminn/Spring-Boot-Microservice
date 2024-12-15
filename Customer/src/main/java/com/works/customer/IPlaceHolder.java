package com.works.customer;

import com.works.customer.models.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "jsonplaceholder", url = "https://jsonplaceholder.org/")
public interface IPlaceHolder {
    @GetMapping("comment/{id}")
    Comment getComments(@PathVariable String id);
}

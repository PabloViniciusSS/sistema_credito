package com.br.createapplicationsystem.controller

import com.br.createapplicationsystem.dto.CustomerDto
import com.br.createapplicationsystem.dto.CustomerUpdateDto
import com.br.createapplicationsystem.dto.CustomerView
import com.br.createapplicationsystem.entity.Customer
import com.br.createapplicationsystem.service.impl.CustomerService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/customers")
class CustomerController(
    private val customerService: CustomerService
) {

    @PostMapping
    fun saveCustomer(@RequestBody customerDto: CustomerDto):String{
      val savedCustomer =  this.customerService.save(customerDto.toEntity())
      return "Customer ${savedCustomer.email} saved!"
    }

    @GetMapping
    fun findById(id: Long): CustomerView {
        val customer: Customer = this.customerService.findById(id)
        return CustomerView(customer)
    }


    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) =  this.customerService.delete(id)

    @PatchMapping
    fun updateCustomer(@RequestParam(value = "customerId")
                       id: Long, @RequestBody customerUpdateDto: CustomerUpdateDto): CustomerView{
        val customer: Customer =this.customerService.findById(id)
        val customerToUpdate: Customer = customerUpdateDto.toEntity(customer)
        val customerUpdated: Customer = this.customerService.save(customerToUpdate)
        return  CustomerView(customerUpdated)
    }

}
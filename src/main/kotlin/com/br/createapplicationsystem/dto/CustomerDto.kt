package com.br.createapplicationsystem.dto

import com.br.createapplicationsystem.entity.Address
import com.br.createapplicationsystem.entity.Customer
import java.math.BigDecimal

data class CustomerDto (
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val income: BigDecimal,
    val email: String,
    val passaword: String,
    val zipcode: String,
    val street: String
){

    fun toEntity():Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.passaword,
        address = Address(
            zipcode = this.zipcode,
            street = this.street
        )
    )
}
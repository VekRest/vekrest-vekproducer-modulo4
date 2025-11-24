package com.vekrest.vekproducer.vekproducer.entities;

import java.time.LocalDate;

public record Client(
        String name,
        LocalDate birth,
        Address address
) {}
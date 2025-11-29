package com.mege.webseer.dtos;

public record RequestCreateUrlByUserDto(
        String userId,
        String url,
        String mode
){}

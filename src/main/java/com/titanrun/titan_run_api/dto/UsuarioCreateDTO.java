package com.titanrun.titan_run_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioCreateDTO (
    @NotBlank String nome,
    @Email @NotBlank String email,
    @NotBlank String senha
){
}

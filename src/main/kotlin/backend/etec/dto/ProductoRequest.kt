package backend.etec.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero
import java.math.BigDecimal

data class ProductoRequest(
    @field:NotBlank(message = "El nombre no puede estar vacío")
    val nombre: String,

    @field:NotBlank(message = "La descripción no puede estar vacía")
    val descripcion: String,

    @field:NotNull(message = "El precio es obligatorio")
    @field:Positive(message = "El precio debe ser mayor a cero")
    val precio: BigDecimal,

    @field:NotNull(message = "El stock es obligatorio")
    @field:PositiveOrZero(message = "El stock no puede ser negativo")
    val stock: Int
)

package backend.etec.dto

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.math.BigDecimal

data class ProductoResponse(
    val id: Long?,
    val nombre: String,
    val descripcion: String,
    val precio: BigDecimal,
    val stock: Int
)

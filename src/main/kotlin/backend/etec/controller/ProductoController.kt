package backend.etec.controller

import backend.etec.dto.ProductoRequest
import backend.etec.dto.ProductoResponse
import backend.etec.service.ProductoService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/productos")
class ProductoController(private val productoService: ProductoService) {

    @GetMapping
    fun listar(pageable: Pageable): Page<ProductoResponse> {
        return productoService.listar(pageable)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ProductoResponse {
        return productoService.buscarPorId(id)
    }

    @PostMapping
    fun crear(@Valid @RequestBody request: ProductoRequest): ResponseEntity<ProductoResponse> {
        val creado = productoService.crear(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(creado)
    }
}

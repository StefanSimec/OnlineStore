package ch.roche.ss.onlinestore.api.service;

import ch.roche.ss.onlinestore.api.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/orders")
public interface OrderServiceAPI
{
    @PostMapping("/place")
    @Operation(summary = "Place new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New order placed successfully", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = {@Content(mediaType = APPLICATION_JSON_VALUE)})
    })
    OrderDTO placeOrder(@RequestBody @Valid OrderDTO orderDTO);

    @GetMapping("/findByTimePeriod")
    @Operation(summary = "Get all orders within the time period")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of orders", content = {@Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class)))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = {@Content(mediaType = APPLICATION_JSON_VALUE)})
    })
    List<OrderDTO> retrieveOrders(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dateFrom,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dateTo);
}

package br.com.wbrq.admin.unidalv.infrastructure.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.wbrq.admin.unidalv.domain.pagination.Pagination;
import br.com.wbrq.admin.unidalv.infrastructure.service.models.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping(value = "services")
@Tag(name = "Service")
public interface ServiceAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new service")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Created successfully"),
        @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
        @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),})
    ResponseEntity<?> createService(@RequestBody CreateServiceRequest input);

    @GetMapping
    @Operation(summary = "List all services paginated")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listed successfully"),
        @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
        @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),})
    Pagination<ServiceListResponse> listServices(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "name") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction
    );

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a service by it's identifier")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Service retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Service was not found"),
        @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),})
    ServiceResponse getById(@PathVariable(name = "id") String id);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a service by it's identifier")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Service updated successfully"),
        @ApiResponse(responseCode = "404", description = "Service was not found"),
        @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),})
    ResponseEntity<?> updateById(@PathVariable(name = "id") String id, @RequestBody UpdateServiceRequest input);

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a service by it's identifier")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Service deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Service was not found"),
        @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),})
    void deleteById(@PathVariable(name = "id") String id);
}

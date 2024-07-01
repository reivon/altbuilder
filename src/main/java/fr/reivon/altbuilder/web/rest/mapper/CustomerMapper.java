package fr.reivon.altbuilder.web.rest.mapper;

import fr.reivon.altbuilder.domain.user.Customer;
import fr.reivon.altbuilder.web.rest.dto.customer.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

    CustomerDto userToUserDto(Customer customer);
    List<CustomerDto> userToUserDto(List<Customer> customers);

}

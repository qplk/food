package com.registration.reg.repository;

import com.registration.reg.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by Stasia on 09.03.17.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByStreetAndBuildingNumberAndRoomNumber(String street, String buildingNumber, String roomNumber);
}

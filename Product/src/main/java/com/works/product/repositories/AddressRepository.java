package com.works.product.repositories;

import com.works.product.entities.Address;
import com.works.product.projections.AddressJoinCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(value = "select\n" +
            "    aid, address, title, cid, code, name\n" +
            "    from PUBLIC.ADDRESS\n" +
            "inner join PUBLIC.ADDRESS_CITIES AC on ADDRESS.AID = AC.ADDRESS_AID\n" +
            "inner join PUBLIC.CITY C on C.CID = AC.CITIES_CID where C.CID = ?1", nativeQuery = true)
        //@Query(value = "call procedureName()", nativeQuery = true)
    List<AddressJoinCity> joinAddress(int cid);
}

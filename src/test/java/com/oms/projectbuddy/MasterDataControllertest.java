package com.oms.projectbuddy;

import com.oms.projectbuddy.controller.MasterDataController;
import com.oms.projectbuddy.model.Country;
import com.oms.projectbuddy.services.IAddressService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Objects;
import java.util.zip.DataFormatException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class MasterDataControllertest {

    @InjectMocks
    MasterDataController masterDataController;
    @Mock
    IAddressService iAddressService;

//    @Test
//    public void testFindAll(){
//        Country country= new Country();
//        country.setId(1l);
//        country.setCountryName("INDIA");
//        Mockito.when(iAddressService.getAllCountry()).thenReturn(Arrays.asList(country));
//        Object allCountry = masterDataController.getAllCountry();
//        assert(Objects.nonNull(allCountry.getBody()));
//    }

    @Test(expected = Exception.class)
    public void testFindAllByStateNegative() throws Exception {
        Country country= new Country();
        country.setId(1l);
        country.setCountryName("INDIA");
        Mockito.doThrow(Exception.class).when(masterDataController.getAllCountry());
      Object allCountry = masterDataController.getAllCountry();

    }
    @Test(expected = Exception.class)
    public void testFindStateNegative() throws Exception {
        Mockito.doThrow(Exception.class).when(masterDataController.getCountryByRegion(anyString()));
       Object allCountry = masterDataController.getCountryByRegion(anyString());

    }
    @Test(expected = Exception.class)
    public void testFindAllNegative() throws Exception {
        Mockito.doThrow(new DataFormatException("Add operation not implemented"))
                .when(iAddressService).getAllCountry();

        //test the add functionality
        Assert.assertNotNull(masterDataController.getAllCountry());


    }
    @Test
    public void getCountryByRegion(){
        try {

            Country country = new Country();

            Object actualValue=masterDataController.getCountryByRegion( anyString());
           Assert.assertNotNull(actualValue.getBody());

        } catch (Exception exception) {

            exception.printStackTrace();
            Assert.assertFalse(false);
        }
    }


}

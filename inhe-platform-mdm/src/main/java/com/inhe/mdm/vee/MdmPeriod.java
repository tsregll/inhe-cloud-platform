package com.inhe.mdm.vee;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="mdmveeperiod")
public class MdmPeriod {
	
   private Integer mdmveeperiod;
   
   public Integer getMdmveeperiod() {
	   return mdmveeperiod;
   }
   public void setMdmveeperiod(Integer mdmveeperiod) {
	   this.mdmveeperiod = mdmveeperiod;
   }
   
   @Override
   public String toString() {
	   return mdmveeperiod.toString();
   }
   

}

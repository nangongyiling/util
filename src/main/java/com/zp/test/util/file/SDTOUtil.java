package com.zp.test.util.file;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.zpark.shop.entity.ProductSpecificationItemValue;

public class SDTOUtil {
	public  static List<ProductSpecificationItemValue> converterValue(Map<Integer,List<String>> maps) throws Exception{
		if(maps==null || maps.size()==0){
			return null;
		}
		List<ProductSpecificationItemValue> psValues=new ArrayList<ProductSpecificationItemValue>();
		int size=0;
		Iterator<Entry<Integer, List<String>>> tempIterator = maps.entrySet().iterator();
		size=tempIterator.next().getValue().size();
		for(int i=0;i<size;i++ ){
			psValues.add(new ProductSpecificationItemValue());
		}
		Iterator<Entry<Integer, List<String>>> it = maps.entrySet().iterator();
		int index = 0;
		while(it.hasNext()){
			Entry<Integer, List<String>> next = it.next();
			for (int i = 0; i < next.getValue().size(); i++) {
				ProductSpecificationItemValue productSpecificationItemValue = psValues.get(i);	
				Method m=ProductSpecificationItemValue.class.getDeclaredMethod("setItemValue_"+index, String.class);
				m.invoke(productSpecificationItemValue, next.getValue().get(i));
			}
			index++;
		}
		return psValues;
	}
}

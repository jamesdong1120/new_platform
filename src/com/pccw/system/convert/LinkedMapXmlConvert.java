package com.pccw.system.convert;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.collections.AbstractCollectionConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

/*******************************************
 * <p>Description: </p>
 * <p>Company:PCCW </p> 
 * @author Jamesdong
 * @date 2016年2月24日 下午3:43:00
 ******************************************/
public class LinkedMapXmlConvert implements Converter  {

	
	/* (non-Javadoc)
	 * @see com.thoughtworks.xstream.converters.ConverterMatcher#canConvert(java.lang.Class)
	 */
	@Override
	public boolean canConvert(Class cls) {
		return cls.equals(HashMap.class)  
				||cls.equals(Map.class)
                || cls.equals(Hashtable.class)  
                || cls.equals(TreeMap.class)  
                || cls.getName().equals("java.util.LinkedHashMap")  
                || cls.getName().equals("sun.font.AttributeMap");
	}

	/* (non-Javadoc)
	 * @see com.thoughtworks.xstream.converters.Converter#marshal(java.lang.Object, com.thoughtworks.xstream.io.HierarchicalStreamWriter, com.thoughtworks.xstream.converters.MarshallingContext)
	 */
	@Override
	public void marshal(Object obj, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		@SuppressWarnings("unchecked")
		Map<String,Object> map=(Map<String,Object>)obj;
		for(Map.Entry<String,Object> entry:map.entrySet()){
			writer.startNode(entry.getKey());
			
			
			if(entry.getValue() instanceof Map){
				doPraseMap(writer, entry.getValue());
			}else{
				writer.setValue(entry.getValue().toString());
			}
			writer.endNode();
		}
		
		
	}

	/* (non-Javadoc)
	 * @see com.thoughtworks.xstream.converters.Converter#unmarshal(com.thoughtworks.xstream.io.HierarchicalStreamReader, com.thoughtworks.xstream.converters.UnmarshallingContext)
	 */
	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		LinkedHashMap<String,Object> map=new LinkedHashMap<String, Object>();
			doPraseXML(reader, map);
		 return map;
		
	}

	private void doPraseMap(HierarchicalStreamWriter writer,Object value){
		
			Map<String,Object> childs=(Map<String, Object>) value;
			for(Map.Entry<String, Object> en:childs.entrySet()){
				writer.startNode(en.getKey());
				if(en.getValue() instanceof Map){
					doPraseMap(writer, en.getValue());
				}else{
					writer.setValue(en.getValue().toString());
				}
				writer.endNode();
		}
		
	}
	private void doPraseXML(HierarchicalStreamReader reader, LinkedHashMap<String, Object> map){
		
		 while (reader.hasMoreChildren()) {  
	            reader.moveDown(); 
	            String key = reader.getNodeName();  
	            Object value = reader.getValue();  
	            if(reader.hasMoreChildren()){
	            	LinkedHashMap<String,Object> map1=new LinkedHashMap<String, Object>();
	            	doPraseXML(reader, map1);
	            	map.put(key,map1);
	            }else{
	            	map.put(key, value);
	            }
	            reader.moveUp();  
	        }  
	}

}

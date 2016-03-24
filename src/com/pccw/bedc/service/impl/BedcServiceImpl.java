package com.pccw.bedc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.bedc.dao.BedcMapper;
import com.pccw.bedc.service.BedcService;
@Service("bedcService")
public class BedcServiceImpl implements BedcService {
	private BedcMapper bedcMapper;
	@Autowired
	public void setBedcMapper(BedcMapper bedcMapper) {
		this.bedcMapper = bedcMapper;
	}
	@Override
	public Integer getConnectPort() {
		Integer port=bedcMapper.getConnectPort();
		System.out.println("portService:+====="+port);
		return port;
	}

}

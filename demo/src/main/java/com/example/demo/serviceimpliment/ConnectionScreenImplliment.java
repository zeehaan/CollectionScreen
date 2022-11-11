package com.example.demo.serviceimpliment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.ConnectionScreen;
import com.example.demo.repositories.ConnectionScreenRepositories;
import com.example.demo.service.ConnectionScreenService;
import com.example.demo.shared.AnchorDetailsDto;
import com.example.demo.shared.BeamDetailsDto;
import com.example.demo.shared.BeamWeldDetailsDto;
import com.example.demo.shared.ConnectionMarkDto;
import com.example.demo.shared.FootingWeldDetailsDto;
import com.example.demo.shared.FreeEndWeldDetails;
import com.example.demo.shared.HoleDetailsDto;
import com.example.demo.shared.Thickness;

@Service
public class ConnectionScreenImplliment implements ConnectionScreenService {
	List<HashMap<String, Object>> mapList = new ArrayList<>();
	@Autowired
	private ConnectionScreenRepositories connectionScreenRepositories;

	@Override
	public   List<HashMap<String, Object>> getConnection() {	
	
		List<HashMap<String, Object>> listOfMapObject = new ArrayList<HashMap<String, Object>>();
		List<ConnectionScreen> conncetionScreenList = connectionScreenRepositories.findAll();
		for (ConnectionScreen connectionScreen : conncetionScreenList) {
			HashMap<String, Object> mapObject = new HashMap<String, Object>();
			String st = connectionScreen.getScreenDetails();
			JSONObject json = new JSONObject(st);
			// CONNECTION-MARK
			String connectionMark = getValueFromKey(json, "connectionDetails.connectionMark.connectionMark");
			// COLUMN-PROFILE
			String columnProfile = getValueFromKey(json, "connectionDetails.connectionMark.columnProfile");
			// general details
			String connectionMarkDtograde = getValueFromKey(json, "connectionDetails.connectionMark.grade");
			String connectionMarkDtoBaseCap = getValueFromKey(json, "connectionDetails.connectionMark.baseOrCapPlate");
			String connectionMarkDtoLocation = getValueFromKey(json, "connectionDetails.connectionMark.location");
			Thickness generalThickness = new Thickness();
			String generalDetailsFraction = getValueFromKey(json, "connectionDetails.connectionMark.thickness.fraction");
			String generalDetailsInch = getValueFromKey(json, "connectionDetails.connectionMark.thickness.inch");
			String generalDetailsMm = getValueFromKey(json, "connectionDetails.connectionMark.thickness.mm");
			generalThickness.setFraction(generalDetailsFraction);
			generalThickness.setInch(generalDetailsInch);
			generalThickness.setMm(generalDetailsMm);
			ConnectionMarkDto connectionMarkDto = new ConnectionMarkDto();
			connectionMarkDto.setBasePlateOrCapPlate(connectionMarkDtoBaseCap);
			connectionMarkDto.setGrade(connectionMarkDtograde);
			connectionMarkDto.setLocation(connectionMarkDtoLocation);
			connectionMarkDto.setThickness(generalThickness);
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("ConnectionMark", connectionMark);
//			map.put("columnProfileValue", columnProfile);
//			map.put("generalDetails", connectionMarkDto);
//			map.put("footingHoleDetails", holeDetailsDto);
//			map.put("footingWeldDetails", footingWeldDetailsDto);
//			map.put("footingAnchorrod", anchorDetailsDto);
//			map.put("beamDetails", "-");
//			map.put("freeEndDetails", "-");
			String location = getValueFromKey(json, "connectionDetails.connectionMark.location");
			String basePlate = getValueFromKey(json, "connectionDetails.connectionMark.baseOrCapPlate");			
			if (location.equals("FOOTING") && basePlate.equals("BASE_PLATE")) {		
				// FOOTING DETAILS
				// Hole-Details
				String parallelToFlange = getValueFromKey(json,
						"connectionDetails.baseplate.footing.holeDetails.noOfHoles.parallelToFlange");
				String parallelToWeb2 = getValueFromKey(json,
						"connectionDetails.baseplate.footing.holeDetails.noOfHoles.parallelToWeb");
				HoleDetailsDto holeDetailsDto = new HoleDetailsDto();
				Thickness footingHoleDetailsThickness = new Thickness();
				String footingHoleDetailsfraction = getValueFromKey(json,
						"connectionDetails.baseplate.footing.holeDetails.holeDia.fraction");
				String footingHoleDetailsinch = getValueFromKey(json, "connectionDetails.baseplate.footing.holeDetails.holeDia.inch");
				String footingHoleDetailsmm = getValueFromKey(json, "connectionDetails.baseplate.footing.holeDetails.holeDia.mm");
				footingHoleDetailsThickness.setFraction(footingHoleDetailsfraction);
				footingHoleDetailsThickness.setInch(footingHoleDetailsinch);
				footingHoleDetailsThickness.setMm(footingHoleDetailsmm);
				holeDetailsDto.setHoleDia(footingHoleDetailsThickness);
				holeDetailsDto.setNoOfHoles(parallelToFlange);
				holeDetailsDto.setParallelToWeb(parallelToWeb2);
				// Weld-Details
				String atFlangeWeldType = getValueFromKey(json, "connectionDetails.baseplate.footing.weldDetails.atFlange.weldType");
				Thickness footingWeldDetailsThickness = new Thickness();
				String footingWeldDetailsFraction = getValueFromKey(json,
						"connectionDetails.baseplate.footing.holeDetails.holeDia.fraction");
				String footingWeldDetailsInch = getValueFromKey(json, "connectionDetails.baseplate.footing.holeDetails.holeDia.inch");
				String footingWeldDetailsMm = getValueFromKey(json, "connectionDetails.baseplate.footing.holeDetails.holeDia.mm");
				footingWeldDetailsThickness.setFraction(footingWeldDetailsFraction);
				footingWeldDetailsThickness.setInch(footingWeldDetailsInch);
				footingWeldDetailsThickness.setMm(footingWeldDetailsMm);
				String atWebWeldType = getValueFromKey(json, "connectionDetails.baseplate.footing.weldDetails.atWeb.weldType");
				Thickness footingWebDetailsThickness = new Thickness();
				String footingWebDetailsFraction = getValueFromKey(json,
						"connectionDetails.baseplate.footing.weldDetails.atFlange.weldSize.fraction");
				String footingWebDetailsInch = getValueFromKey(json,
						"connectionDetails.baseplate.footing.weldDetails.atFlange.weldSize.inch");
				String footingWebDetailsMm = getValueFromKey(json,
						"connectionDetails.baseplate.footing.weldDetails.atFlange.weldSize.mm");
				footingWebDetailsThickness.setFraction(footingWebDetailsFraction);
				footingWebDetailsThickness.setInch(footingWebDetailsInch);
				footingWebDetailsThickness.setMm(footingWebDetailsMm);
				FootingWeldDetailsDto footingWeldDetailsDto = new FootingWeldDetailsDto();
				footingWeldDetailsDto.setAtFlangeWeldSize(footingWeldDetailsThickness);
				footingWeldDetailsDto.setAtFlangeWeldType(atFlangeWeldType);
				footingWeldDetailsDto.setAtWebWeldSize(footingWebDetailsThickness);
				footingWeldDetailsDto.setAtWebWeldType(atWebWeldType);
				// Anchor Details
				String anchorRodType = getValueFromKey(json, "connectionDetails.baseplate.footing.anchorRod.details.type");
				String anchorRodGrade = getValueFromKey(json, "connectionDetails.baseplate.footing.anchorRod.details.grade");
				AnchorDetailsDto anchorDetailsDto = new AnchorDetailsDto();
				Thickness footingAnchorThickness = new Thickness();
				String footingAnchorFraction = getValueFromKey(json,
						"connectionDetails.baseplate.footing.anchorRod.details.dia.fraction");
				String footingAnchorInch = getValueFromKey(json, "connectionDetails.baseplate.footing.anchorRod.details.dia.inch");
				String footingAnchorMm = getValueFromKey(json, "connectionDetails.baseplate.footing.anchorRod.details.dia.mm");
				footingAnchorThickness.setFraction(footingAnchorFraction);
				footingAnchorThickness.setInch(footingAnchorInch);
				footingAnchorThickness.setMm(footingAnchorMm);
				anchorDetailsDto.setAnchorRodDia(footingAnchorThickness);
				anchorDetailsDto.setAnchorRodGrade(anchorRodGrade);
				anchorDetailsDto.setType(anchorRodType);
				mapObject.put("Connection-Mark", connectionMark);
				mapObject.put("Column-Profile",columnProfile );
				mapObject.put("General-Details",connectionMarkDto);						
				// Hole-Details
				mapObject.put("Hole-Details", holeDetailsDto);
//				String holeDia = footingHoleDetailsinch + footingHoleDetailsmm + footingHoleDetailsfraction;
//				String noOfHoles = parallelToFlange;
//				String parallelToWeb = parallelToWeb2;
				// Weld-Details
				mapObject.put("Weld-Details",footingWeldDetailsDto);
//				String FootingAtFlangeWeldType = atFlangeWeldType;
//				String FootingAtFlangeWeldSize = footingWeldDetailsMm + footingWeldDetailsInch + footingWeldDetailsFraction;
//				String FootingAtWebWeldType = atWebWeldType;
//				String FootingAtWebWeldSize = footingWebDetailsMm + footingWebDetailsInch + footingWebDetailsFraction;
				// Anchor-Rod
				mapObject.put("Anchor-Rod",anchorDetailsDto);
//				String Type = anchorRodType;
//				String Dia = footingAnchorInch + footingAnchorMm + footingAnchorFraction;
//				String Grade = anchorRodGrade;				
				mapObject.put("BeamDetails",null);
				mapObject.put("FreeEndDetails",null);
//				String BeamDetails=null;
//				String FreeEndDetails=null;
				listOfMapObject.add(mapObject);
			}
			
			if (location.equals("BEAM") && basePlate.equals("BASE_PLATE")) {
				// BEAM DETAILS
				// Bolt-Details
				
				String beamBoltGrade = getValueFromKey(json, "connectionDetails.baseplate.beam.boltDetails.boltDetails.boltGrade");
				String beamBoltSpacing = getValueFromKey(json,
						"connectionDetails.baseplate.beam.boltDetails.boltDetails.boltSpacingRows.defaultValue.inch");
				BeamDetailsDto beamDetailsDto = new BeamDetailsDto();
				Thickness beamBoltThickness = new Thickness();
				
				String beamBoltInch = getValueFromKey(json, "connectionDetails.baseplate.beam.boltDetails.boltDetails.boltDia.inch");
				String beamBoltMm = getValueFromKey(json, "connectionDetails.baseplate.beam.boltDetails.boltDetails.boltDia.mm");
				
				beamBoltThickness.setInch(beamBoltInch);
				beamBoltThickness.setMm(beamBoltMm);
				beamDetailsDto.setBeamBoltDia(beamBoltThickness);
				beamDetailsDto.setBeamBoltGrade(beamBoltGrade);
				beamDetailsDto.setBeamBoltSpacing(beamBoltSpacing);
				// Weld-Details
				
				String atBeamFlangeWeldType = getValueFromKey(json, "connectionDetails.baseplate.beam.weldDetails.atFlange.weldType");
				String atBeamWebWeldType = getValueFromKey(json, "connectionDetails.baseplate.beam.weldDetails.atWeb.weldType");
				Thickness beamWeldFlangeThickness = new Thickness();
				String beamWeldFlangeFraction = getValueFromKey(json,
						"connectionDetails.baseplate.beam.weldDetails.atFlange.weldSize.fraction");
				String beamWeldFlangeInch = getValueFromKey(json,
						"connectionDetails.baseplate.beam.weldDetails.atFlange.weldSize.inch");
				String beamWeldFlangeMm = getValueFromKey(json, "connectionDetails.baseplate.beam.weldDetails.atFlange.weldSize.mm");
				beamWeldFlangeThickness.setMm(beamWeldFlangeMm);
				beamWeldFlangeThickness.setInch(beamWeldFlangeInch);
				beamWeldFlangeThickness.setFraction(beamWeldFlangeFraction);
				Thickness beamWeldWebThickness = new Thickness();
				String beamWeldWebFraction = getValueFromKey(json,
						"connectionDetails.baseplate.beam.weldDetails.atWeb.weldSize.fraction");
				String beamWeldWebInch = getValueFromKey(json, "connectionDetails.baseplate.beam.weldDetails.atWeb.weldSize.inch");
				String beamWeldWebMm = getValueFromKey(json, "connectionDetails.baseplate.beam.weldDetails.atWeb.weldSize.mm");
				beamWeldWebThickness.setMm(beamWeldWebMm);
				beamWeldWebThickness.setInch(beamWeldWebInch);
				beamWeldWebThickness.setFraction(beamWeldWebFraction);				
								
				BeamWeldDetailsDto beamWeldDetailsDto = new BeamWeldDetailsDto();
				beamWeldDetailsDto.setBeamAtFlangeWeldSize(beamWeldFlangeThickness);
				beamWeldDetailsDto.setBeamAtFlangeWeldType(atBeamFlangeWeldType);
				beamWeldDetailsDto.setBeamAtWebWeldSize(beamWeldWebThickness);
				beamWeldDetailsDto.setBeamAtWebWeldType(atBeamWebWeldType);
			
			
				
				
				mapObject.put("Connection-Mark", connectionMark);
				mapObject.put("Column-Profile",columnProfile );
				mapObject.put("General-Details",connectionMarkDto);				
				// Bolt-Details
				mapObject.put("Bolt-Details",beamDetailsDto);
//				String Space = beamBoltSpacing;
//				String Dia = beamBoltFraction + beamBoltInch + beamBoltMm;
//				String Grade = beamBoltGrade;
				// Weld-Details
				mapObject.put("Weld-Details",beamWeldDetailsDto);
//				String AtFlangeWeldType = atBeamFlangeWeldType;
//				String AtFlangeWeldSize = beamWeldFlangeMm + beamWeldFlangeFraction + beamWeldFlangeInch;
//				String AtwebWeldType = atBeamWebWeldType;
//				String AtwebWeldSize = beamWeldWebMm + beamWeldWebInch + beamWeldWebFraction;				
				mapObject.put("FootingDetails",null);
				mapObject.put("FreeEndDetails",null);
//				String FootingDetails=null;
//				String FreeEndDetails=null;		
				listOfMapObject.add(mapObject);
			}
		
			
			if (location.equals("BEAM") && basePlate.equals("CAP_PLATE")) {		
				// BEAM DETAILS
				// Bolt-Details
				String beamBoltGrade = getValueFromKey(json, "connectionDetails.baseplate.beam.boltDetails.boltDetails.boltGage.boltGrade");
				String beamBoltSpacing = getValueFromKey(json,
						"connectionDetails.baseplate.beam.boltDetails.boltSpacingRows.defaultValue.inch");
				BeamDetailsDto beamDetailsDto = new BeamDetailsDto();
				Thickness beamBoltThickness = new Thickness();
				String beamBoltFraction = getValueFromKey(json, "connectionDetails.baseplate.beam.boltDetails.boltDia.fraction");
				String beamBoltInch = getValueFromKey(json, "connectionDetails.baseplate.beam.boltDetails.boltDia.inch");
				String beamBoltMm = getValueFromKey(json, "connectionDetails.baseplate.beam.boltDetails.boltDia.mm");
				beamBoltThickness.setFraction(beamBoltFraction);
				beamBoltThickness.setInch(beamBoltInch);
				beamBoltThickness.setMm(beamBoltMm);
				beamDetailsDto.setBeamBoltDia(beamBoltThickness);
				beamDetailsDto.setBeamBoltGrade(beamBoltGrade);
				beamDetailsDto.setBeamBoltSpacing(beamBoltSpacing);
				mapObject.put("Connection-Mark", connectionMark);
				mapObject.put("Column-Profile",columnProfile );
				mapObject.put("General-Details",connectionMarkDto);						
				mapObject.put("Bolt-Details",beamDetailsDto);
//				String Space = beamBoltSpacing;
//				String Dia =beamBoltInch + beamBoltMm;
//				String Grade = beamBoltGrade;
				
				// Weld-Details
				String allAroundBeamType = getValueFromKey(json, "connectionDetails.baseplate.beam.weldDetails.allAround.weldType");				
				Thickness weldBeamThickness = new Thickness();
				String weldBeamFraction = getValueFromKey(json,
						"connectionDetails.baseplate.beam.weldDetails.allAround.weldSize.fraction");
				String weldBeamInch = getValueFromKey(json, "connectionDetails.baseplate.beam.weldDetails.allAround.weldSize.inch");
				String weldBeamMm = getValueFromKey(json, "connectionDetails.baseplate.beam.weldDetails.allAround.weldSize.mm");
				weldBeamThickness.setFraction(weldBeamFraction);
				weldBeamThickness.setInch(weldBeamInch);
				weldBeamThickness.setMm(weldBeamMm);
				BeamWeldDetailsDto beamWeldDetailsDto = new BeamWeldDetailsDto();			
				beamWeldDetailsDto.setAllAroundaWeldType(allAroundBeamType);
				beamWeldDetailsDto.setAllAroundaWeldSize(weldBeamThickness);
				mapObject.put("Connection-Mark", connectionMark);
				mapObject.put("Column-Profile",columnProfile );
				mapObject.put("General-Details",connectionMarkDto);						
				mapObject.put("Weld-Details",beamWeldDetailsDto);				
//				String AllAroundWeldType = allAroundBeamType;
//				String AllAroundWedSize = weldBeamFraction + weldBeamInch + weldBeamMm;				
				mapObject.put("FootingDetails",null);
				mapObject.put("FreeEndDetails",null);
//				String FootingDetails=null;
//				String FreeEndDetails=null;		
				listOfMapObject.add(mapObject);
			}
			

			if (location.equals("FREE_END") && basePlate.equals("CAP_PLATE")) {				
				mapObject.put("Connection-Mark", connectionMark);
				mapObject.put("Column-Profile",columnProfile );
				mapObject.put("General-Details",connectionMarkDto);	
				String freeEndAtFlangeWeldType = getValueFromKey(json,
						"connectionDetails.capPlate.freeEnd.weldDetails.atFlange.weldType");
				String freeEndAtFlangeWeldSize = getValueFromKey(json,
						"connectionDetails.capPlate.freeEnd.weldDetails.atFlange.weldSize.inch");
				String freeEndAtWebWeldType = getValueFromKey(json, "connectionDetails.capPlate.freeEnd.weldDetails.atWeb.weldType");
				String freeEndAtWebWeldSize = getValueFromKey(json,
						"connectionDetails.capPlate.freeEnd.weldDetails.atWeb.weldSize.inch");
				FreeEndWeldDetails freeEndWeldDetails = new FreeEndWeldDetails();
				freeEndWeldDetails.setFreeEndAtFlangeWeldSize(freeEndAtFlangeWeldSize);
				freeEndWeldDetails.setFreeEndAtFlangeWeldType(freeEndAtFlangeWeldType);
				freeEndWeldDetails.setFreeEndAtWebWeldSize(freeEndAtWebWeldSize);
				freeEndWeldDetails.setFreeEndAtWebWeldType(freeEndAtWebWeldType);
				// Weld-Details
				mapObject.put("Weld-Details",freeEndWeldDetails);
//				String AtFlangeWeldType = freeEndAtFlangeWeldType;
//				String AtFlangeWeldSize = freeEndAtFlangeWeldSize;
//				String AtwebWeldType = freeEndAtWebWeldType;
//				String AtwebWeldSize = freeEndAtWebWeldSize;				
				mapObject.put("FootingDetails",null);
				mapObject.put("BeamDetails",null);
//				String FootingDetails=null;
//				String BeamDetails=null;
				listOfMapObject.add(mapObject);
			}

		}
		
		 return listOfMapObject;
	
	
		

	}

	public String getValueFromKey(JSONObject jsonObject, String address) {

		String key;
		List<String> addressKeys = new LinkedList<>(Arrays.asList(address.split("\\.")));
		key = addressKeys.get(0);
		Object obj = jsonObject.get(key);
		if (obj instanceof JSONObject) {
			addressKeys.remove(key);
			return getValueFromKey((JSONObject) obj, String.join(".", addressKeys));
		} else
			return obj.toString();
	}

	
}

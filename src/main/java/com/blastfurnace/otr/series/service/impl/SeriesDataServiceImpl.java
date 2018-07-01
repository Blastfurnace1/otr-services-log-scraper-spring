package com.blastfurnace.otr.series.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blastfurnace.otr.data.series.SeriesService;
import com.blastfurnace.otr.data.series.model.Series;
import com.blastfurnace.otr.data.series.service.model.SeriesDataWrapper;
import com.blastfurnace.otr.rest.request.QueryData;
import com.blastfurnace.otr.series.service.SeriesDataService;
import com.blastfurnace.otr.service.GenericService;
import com.blastfurnace.otr.service.response.GenericResponse;

@Component("SeriesDataService")
public class SeriesDataServiceImpl implements SeriesDataService {

	@Autowired
	private SeriesService service;

	private GenericService<Series> gService;

	public SeriesDataServiceImpl() {
		gService = new GenericService<Series>(Series.fieldDefinitions);
	}

	/* (non-Javadoc)
	 * @see com.blastfurnace.otr.rest.adapter.SeriesDataAdapter#query(com.blastfurnace.otr.rest.request.QueryData)
	 */
	@Override
	public GenericResponse<List<Map<String,Object>>> query(QueryData qry) {
		return gService.query(qry, service);
	}

	/* (non-Javadoc)
	 * @see com.blastfurnace.otr.rest.adapter.SeriesDataAdapter#getResultsCount(com.blastfurnace.otr.rest.request.QueryData)
	 */
	@Override
	public GenericResponse<Long> getResultsCount(QueryData qry) {
		return gService.getResultsCount(qry, service);
	}

	/* (non-Javadoc)
	 * @see com.blastfurnace.otr.rest.adapter.SeriesDataAdapter#delete(java.lang.Long)
	 */
	@Override
	public GenericResponse<String> delete(Long  id) {
		return gService.delete(id, service);
	}

	/* (non-Javadoc)
	 * @see com.blastfurnace.otr.rest.adapter.SeriesDataAdapter#save(com.blastfurnace.otr.model.Series)
	 */
	@Override
	public GenericResponse<SeriesDataWrapper> save(SeriesDataWrapper series) {
		GenericResponse<SeriesDataWrapper> response = new GenericResponse<SeriesDataWrapper>(null);
		if (series == null) {
			response.setStatus(-50l);
			response.setMessage("Unable to save Record - nothing to save");
			return response;
		} 
		try {
			SeriesDataWrapper newSeries = service.save(series);
			response.setPayload(newSeries);
			if (newSeries == null) {
				response.setStatus(-50l);
				response.setMessage("Unable to save Record");
			}
		} catch (Exception e) {
			response.setStatus(-10l);
			response.setMessage("An Error Occurred - unable to get a result");
			response.setErrorOccured(true);
			response.addError(e.getMessage());
		}

		return response;
	}
	

	/* (non-Javadoc)
	 * @see com.blastfurnace.otr.rest.adapter.SeriesDataAdapter#get(java.lang.Long)
	 */
	@Override
	public GenericResponse<SeriesDataWrapper> get(Long id) {
		GenericResponse<SeriesDataWrapper> response = new GenericResponse<SeriesDataWrapper>(null);
		try {
			SeriesDataWrapper series = service.getComplex(id);
			response.setPayload(series);

			if (series == null) {
				response.setStatus(10l);
				response.setMessage("No Results found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(-10l);
			response.setMessage("An Error Occurred - unable to get a result");
			response.setErrorOccured(true);
			response.addError(e.getMessage());
		}

		return response;
	}

}

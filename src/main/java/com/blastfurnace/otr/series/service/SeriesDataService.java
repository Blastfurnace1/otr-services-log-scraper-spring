package com.blastfurnace.otr.series.service;

import java.util.List;
import java.util.Map;

import com.blastfurnace.otr.data.series.service.model.SeriesDataWrapper;
import com.blastfurnace.otr.rest.request.QueryData;
import com.blastfurnace.otr.service.response.GenericResponse;

public interface SeriesDataService {

	GenericResponse<SeriesDataWrapper> get(Long id);

	GenericResponse<List<Map<String, Object>>> query(QueryData qry);

	GenericResponse<Long> getResultsCount(QueryData qry);

	GenericResponse<String> delete(Long id);

	GenericResponse<SeriesDataWrapper> save(SeriesDataWrapper series);

}
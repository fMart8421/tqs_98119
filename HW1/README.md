# InciVID-19

## *API and usage*

`https://services1.arcgis.com/0MSEUqKaxRlEPj5g/arcgis/rest/services/ncov_cases2_v1/FeatureServer/2/query?where=Country_Region%20%3D%20'<country_name>'&outFields=<output_fields>&outSR=4326&f=json`  

Where `<country_name>` is supposed to be the name of the country, according to the API's standards, and `<output_fields>` is supposed to be the output fields, separated by commas.  

### *Output Fields*  

These are the output fields selected for the present project:  

* Country_Region
* Confirmed
* Deaths
* Recovered
* Active
* Incident_Rate
* Last_Update  

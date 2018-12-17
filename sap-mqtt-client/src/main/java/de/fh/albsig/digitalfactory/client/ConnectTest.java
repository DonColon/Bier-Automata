package de.fh.albsig.digitalfactory.client;

import java.util.ArrayList;

import com.sap.conn.jco.*;


public class ConnectTest {

	public static void main(String[] args) throws JCoException {
		// Initialisieren und Testen der Verbindung
		JCoDestination d = JCoDestinationManager.getDestination("src/main/resources/ConProperties");
		d.ping();
		System.out.println("Attributes: " + "\n\n" + d.getAttributes());

//		ArrayList<String> result = getStringValues("MARA", "*", "MATNR"));
		
		JCoRepository repository = d.getRepository();
		
		// Transaktionsnummer um Baustein anzuschauen: SE37
		JCoFunction function = repository.getFunction("Z_NEXTOP_BYORDER1");	
		
		//JCoFunction function = repository.getFunction("BAPI_FLIGHT_CHECKAVAILIBILITY");
		 
		JCoParameterList input = function.getImportParameterList(); 
		//input.setValue("AIRLINEID","LH");
		//input.setValue("FLIGHTDATE","20140430");
		input.setValue("I_AUFNR","1000006");		
		 
		// Funktion aufrufen
		function.execute(d);
		  
		JCoParameterList valuelist = function.getExportParameterList();
		//JCoParameterList table = function.getTableParameterList();
		//JCoTable jcotable = table.getTable("RETURN");

		//for (int i = 0; i < jcotable.getNumRows(); i++) {
			//String string = jcotable[i];
		//	jcotable.setRow(i);
		//	System.out.println(jcotable.getValue("MESSAGE"));
		//}
		System.out.println(valuelist.getValue("E_VORNR"));
		System.out.println(valuelist.getValue("E_TEMP"));
		System.out.println(valuelist.getValue("E_DAUER"));
		System.out.println(valuelist.getValue("E_SWITCH1"));
		System.out.println(valuelist.getValue("E_SWITCH2"));
		System.out.println(valuelist.getValue("E_DESCRIPTION"));
		
		//for (JCoField jCoField : jcotable) {
		//	System.out.println(jCoField.getValue());
		//}			

		
		
//		for (JCoField jCoField : table) {
//			System.out.println(jCoField.getValue());
//		}
		
	}

	
/*	
    // Methode ermittelt Liste von Stringwerten zu einer Selektion (selOpt)
    // Beispiel: Auftragsnummern zu einer Selektion (z.B. matNo)
    public static ArrayList<String> getStringValues(String tblNo, String selOpt, String fieldName) throws JCoException {

        ArrayList<String> as = new ArrayList<String>();
        JCoRepository repos = sapErp.getRepository();        
        JCoFunction function = repos.getFunction("RFC_READ_TABLE");
        if(function == null)
           throw new AbapException("JCO_ERROR_FUNCTION_NOT_FOUND");

        function.getImportParameterList().setValue("QUERY_TABLE", tblNo);
        JCoParameterList tableParameters  = function.getTableParameterList();
        JCoTable options = tableParameters.getTable("OPTIONS");
        options.appendRow();
        options.setValue( "TEXT", selOpt );           
        
        JCoTable fields = tableParameters.getTable("FIELDS");
        fields.appendRow();
        fields.setValue( "FIELDNAME", fieldName );
        
        function.execute(sapErp);

        JCoTable resultData = tableParameters.getTable("DATA");
        int nRows = resultData.getNumRows();
        for (int i = 0; i < nRows; i++) {
           resultData.setRow(i);
           as.add(resultData.getString("WA"));
        }

        return as;     
    }
*/
	
}

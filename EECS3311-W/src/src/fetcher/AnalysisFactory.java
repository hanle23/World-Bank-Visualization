package src.fetcher;

import javax.swing.JOptionPane;

import src.analyses.CO2EmissionVsGDP;
import src.analyses.CO2_Energy_PM25;
import src.analyses.HealthCareVsMortality;
import src.analyses.avgGovExpenditureOnEd;
import src.analyses.forestArea;
import src.analyses.healthVsBeds;
import src.analyses.infantMortality;
import src.analyses.problemsAccessingHealthCare;
import src.interfaces.analyses;

public class AnalysisFactory {
	public analyses getAnalysis(int startYear, int endYear, String countryCode, String analysisType) {
		
		System.out.println("Start Year: "+ startYear + " end Year: "+ endYear + " Country code: "+ countryCode+ "Analysis type: "+ analysisType);
		if(analysisType == null || startYear == -1 || endYear == -1 || countryCode == null) {
			JOptionPane.showMessageDialog(null, "One or more fields inputted is invalid", "Invaild Input", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		
		if(analysisType.equalsIgnoreCase("Infant Mortality"))
			return new infantMortality(startYear, endYear, countryCode);
		if(analysisType.equalsIgnoreCase("Problems Accessing Health Care"))
			return new problemsAccessingHealthCare(startYear, endYear, countryCode);
		if(analysisType.equalsIgnoreCase("Percent of Forest Area"))
			return new forestArea(startYear, endYear, countryCode);
		if(analysisType.equalsIgnoreCase("Average Government Expenditure on Education"))
			return new avgGovExpenditureOnEd(startYear, endYear, countryCode);
		if(analysisType.equalsIgnoreCase("CO2 Emissions per unit of GDP"))
			return new CO2EmissionVsGDP(startYear, endYear, countryCode);
		if(analysisType.equalsIgnoreCase("Problems Accessing Health Care vs Infant Mortality"))
			return new HealthCareVsMortality(startYear, endYear, countryCode);
		if(analysisType.equalsIgnoreCase("Percentage change of CO2 Emissions + Energy Use + Air Pollution"))
			return new CO2_Energy_PM25(startYear, endYear, countryCode);
		if(analysisType.equalsIgnoreCase("Ratio of Health Expenditure to Hospital Beds"))
			return new healthVsBeds(startYear, endYear, countryCode);
		
		return null;
		
	}
}

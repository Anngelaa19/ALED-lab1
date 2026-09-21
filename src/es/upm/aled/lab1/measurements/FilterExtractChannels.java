package es.upm.aled.lab1.measurements;

/**
 * Filter that extracts the specified channels from an EEGModel.
 * 
 * @author mmiguel, rgarciacarmona
 *
 */
public class FilterExtractChannels implements Filter {

	private int[] validChannels;

	/**
	 * Builds the Filter. The use from an array of valid channels.
	 * 
	 * @param validChannels The channel numbers to be extracted, starting from 0.
	 */
	public FilterExtractChannels(int[] validChannels) {
		this.validChannels = validChannels; 
		
	}

	@Override
	public EEGModel applyFilter(EEGModel eeg) {
		Measurement[] measurements = eeg.getMeasurements();
		Measurement[] filtro = new Measurement[measurements.length];
		for (int i=0; i<measurements.length; i++) {
			float filteredChannels[] = new float[validChannels.length];
			int iFilteredChannels=0; 
			Measurement measurement = measurements[i];
			for (int iChannels = 0; iChannels < measurement.numChannels(); iChannels++) {
				boolean chosen = false;
				for (int j =0; j<validChannels.length;j++) {
					if (iChannels == validChannels[j]) {
						chosen = true;
						break;
					}
				}
				if(chosen) {
					filteredChannels[iFilteredChannels]=measurement.getChannel(iChannels);
					iFilteredChannels++;
					
				}
			}
			filtro[i] = new Measurement(filteredChannels);
		}
		EEGModel filteredModel = new EEGModel(filtro);
		return filteredModel;
	}

}

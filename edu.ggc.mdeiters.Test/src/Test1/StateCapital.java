package Test1;

import java.io.Serializable;

public class StateCapital implements Serializable {
    private String capital;
    private String state;

    public StateCapital() {

    }

    /**
     * @param Capital
     * @param state
     */
    public StateCapital( String capital, String state ) {

        this.capital = capital;
        this.state = state;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital( String capital ) {
        this.capital = capital;
    }

    public String getState() {
        return state;
    }

    public void setState( String state ) {
        this.state = state;
    }

    public int hashCode() {

        int hashCode = 0;

        for ( int i = 0; i < this.state.length(); i++ ) {

            hashCode += this.state.charAt(i) / 10;
        }

        for ( int i = 0; i < this.capital.length(); i++ ) {

            hashCode += this.capital.charAt(i) / 8;
        }

        return hashCode;
    }

}

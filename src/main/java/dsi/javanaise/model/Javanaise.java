package dsi.javanaise.model;


public class Javanaise {

    private String motFrancais;
    private String motJavanaise;

    public Javanaise()
    {
        this.motFrancais = "";
        this.motJavanaise = "";
    }

    public Javanaise(String motFrancais)
    {
        this.motFrancais = motFrancais;
        this.motJavanaise = "";
    }

    @Override
    public int hashCode() {
        final int a = 31;
        int result = 1;
        result = a + this.motFrancais.length() + this.motJavanaise.length();
        return result;
    }

    public String getMotFrancais() {
        return motFrancais;
    }

    public String getMotJavanaise()
    {
        return motJavanaise;
    }
    public void translateToJavanaise()
    {
        System.out.println("translateToJavanaise called " + this.motFrancais);

        System.out.println("translateToJavanaise called javanaise '" + this.motJavanaise + "'");

        if(this.motFrancais.equals(""))
            this.motJavanaise = "";

        if(this.motJavanaise.equals(""))
        {
            this.motJavanaise = toJavanaise(this.motFrancais);
        }
    }

    public String toString()
    {
        return "'" + motFrancais + " - '" + motJavanaise +"'";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Javanaise other = (Javanaise) obj;

        return this.motFrancais.equals(other.getMotFrancais());
    }

    private String toJavanaise(String motFrancais)
    {

        return "'" + motFrancais + "' à traduire" ;
    }


}

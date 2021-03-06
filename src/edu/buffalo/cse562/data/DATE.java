package edu.buffalo.cse562.data;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class DATE extends Datum {

    private static final long serialVersionUID = -4880592342416509L;

    //    Date d;
    String s;

    /**
     * use STRING type instead. DATES are being handled as STRINGS throughout the code
     */
    private DATE(String s) {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        this.d = df.parse(s);
        this.s = s;
    }

    /**
     * only to be used for externalization. Do not use this
     */
    public DATE() {
    }

    @Override
    public boolean toBOOL() throws CastException {
        throw new CastException();
    }

    @Override
    public Long toLONG() throws CastException {
        throw new CastException();
    }

    @Override
    public Double toDOUBLE() throws CastException {
        throw new CastException();
    }

    @Override
    public type getType() {
        return Datum.type.DATE;
    }

    @Override
    public int customHash(int hashFactor) {
        return s.length() % hashFactor;
    }

    @Override
    public String toSTRING() {
        return s;
    }

    @Override
    public int compareTo(Datum datum) {
        return s.compareTo(((DATE) datum).s);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof DATE) && s.equals(((DATE) obj).toSTRING());
    }

    @Override
    public int hashCode() {
        return s.hashCode();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(s);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        s = in.readUTF();
    }
}

package au.edu.federation.utils;

import au.edu.federation.caliko.FabrikChain;
import au.edu.federation.caliko.FabrikStructure;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Utility class to marshall and unmarshall a chain or structure via JAXB.
 *
 * @author jsalvo
 */
public class MarshallingUtil {

    private MarshallingUtil() {
        //
    }

    /**
     * Marshalls a FabrikChain as XML to an OutputStream
     *
     * @param <T>   The type of FabrikChain
     * @param chain The FabrikChain to marshall
     * @param os    The OutputStream to write out the XML to
     * @throws JAXBException Any problem marshalling the FabrikChain
     */
    @SuppressWarnings("rawtypes")
    public static <T extends FabrikChain> void marshall(final T chain, final OutputStream os) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(chain.getClass());
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        m.marshal(chain, os);
    }

    /**
     * Marshalls a FabrikStructure as XML to an OutputStream
     *
     * @param <T>   The type of FabrikStructure
     * @param chain The FabrikStructure to marshall
     * @param os    The OutputStream to write out the XML to
     * @throws JAXBException Any problem marshalling the FabrikStructure
     */
    @SuppressWarnings("rawtypes")
    public static <T extends FabrikStructure> void marshall(final T chain, final OutputStream os) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(chain.getClass());
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        m.marshal(chain, os);
    }

    /**
     * Unmarshalls a FabrikChain in XML from an InputStream
     *
     * @param <T>   The type of FabrikChain
     * @param is    The InputStream of the XML to unmarshall from
     * @param clazz The type of FabrikChain that must be unmarshalled
     * @return The FabrikChain unmarshalled from the InputStream
     * @throws JAXBException Any problem unmarshalling the FabrikChain
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T extends FabrikChain> T unmarshallChain(final InputStream is, Class<T> clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller m = context.createUnmarshaller();

        T chain = (T) m.unmarshal(is);
        chain.updateChainLength();
        return chain;
    }

    /**
     * Unmarshalls a FabrikStructure in XML from an InputStream
     *
     * @param <T>   The type of FabrikStructure
     * @param is    The InputStream of the XML to unmarshall from
     * @param clazz The type of FabrikStructure that must be unmarshalled
     * @return The FabrikStructure unmarshalled from the InputStream
     * @throws JAXBException Any problem unmarshalling the FabrikStructure
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T extends FabrikStructure> T unmarshallStructure(final InputStream is, Class<T> clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller m = context.createUnmarshaller();

        T structure = (T) m.unmarshal(is);
        for (int i = 0; i < structure.getNumChains(); i++) {
            structure.getChain(i).updateChainLength();
        }
        return structure;
    }
}

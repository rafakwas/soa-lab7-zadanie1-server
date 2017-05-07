import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.jms.*;
import java.util.List;

@ManagedBean
@ApplicationScoped
@Named("listcontroller")
public class ListControllerBean {

    @EJB(lookup = "java:global/repository-test/RepositoryTestBean!RepositoryRemote")
    RepositoryRemote repositoryRemote;

    private List<Integer> topics;

    final static String JMS_USERNAME="user";
    final static String JMS_PASSWORD="user";
    final static String WILDFLY_REMOTING_URL="http-remoting://localhost:8080";

    @Resource(mappedName = "java:/myJmsTest/MyConnectionFactory")
    private TopicConnectionFactory cf;
    @Resource(mappedName = "java:/myJmsTest/MyTopic")
    private Topic topicExample;
    private TopicConnection connection;

    public void addRow() {
        repositoryRemote.addTopic();
    }

    public void addMessage(int number) {
        System.out.println("addMessage to topic " + number);
        System.out.println("JMSService: sendMessage START");
        try {
            connection = cf.createTopicConnection(JMS_USERNAME,JMS_PASSWORD);
            TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            TopicPublisher publisher = session.createPublisher(topicExample);
            publisher.setTimeToLive(300000);
            connection.start();
            TextMessage message = session.createTextMessage("Wiadomość do subskrybentów topicu nr " + number);
            message.setStringProperty("topicIdentifier",String.valueOf(number));
            publisher.publish(message);
            System.out.println("message send through publisher");
        }
        catch (Exception exc) {
            System.out.println("Błąd w wysyłaniu wiadomosci:" + exc.toString());
        }
        finally {if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (JMSException e) {
                System.out.println("BŁĄD w zamykaniu połączenia: " + e.toString());
            }
        }
        }
        System.out.println("JMSService: sendMessage END");
    }



    /*-------------------------------------------------------------------------------*/
    public List<Integer> getTopics() {
        return repositoryRemote.getTopics();
    }
}

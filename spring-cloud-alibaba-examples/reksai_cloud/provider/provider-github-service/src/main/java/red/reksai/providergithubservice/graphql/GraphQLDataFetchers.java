package red.reksai.providergithubservice.graphql;


import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;
/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-05-04 20:50
 * @since :
 */


@Component
public class GraphQLDataFetchers {


    public DataFetcher getHelloWorldDataFetcher() {
        return environment -> "world";
    }

    public DataFetcher getEchoDataFetcher() {
        return environment -> environment.getArgument("toEcho");
    }


}

# Microservice Sample

 

This project is created to learn & practise about microservice architecture on java platform.

 

## Controller Endpoints



| Request type  | Endpoint                      | Returns  |

| ------------- |:----------------------------- | -----:|

| get           | /kivi-client/                 | List of all counts asc order |

| get           | /kivi-client/?sorting=desc    | List of all counts desc order |

| post          | /kivi-client/{number}         | Save Count with provided number |

| delete         | /kivi-client/{number}        |  Delete Count by provided number |

| get            |/kivi-client/biggestCount     | Biggest numbered count |

| get            |/kivi-client/lowestCount      | Lowest numbered count |

| get           | /kivi-client/{number}         | Count with provided number |

 

## Getting Started

 

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

 

### Prerequisites

 

- MongoDb ( Up and Running service)

- Java

- Maven

- You Favorite IDE

 

### Running

 

First, clone the project to your local pc.

```

# git clone xxxxx

```

Then start mongodb

```

# service mongod start

```

After that, start server then start client1 project.

 

## Running the tests

 

There are tests for service & controller classes. you can run them via

```

mvn clean install

```

 

## Built With

 

* [Spring](https://spring.io) - The web framework used

* [Maven](https://maven.apache.org/) - Dependency Management

* [MongoDB](https://www.mongodb.com/) - Database

 

## License

 

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

 

# Arquitetura Hexagonal - POC

### Sobre a arquitetura hexagonal
Também chamada de Ports and Adapters, a arquitetura hexagonal é uma forma de organizar o código em camadas, cada qual com a sua responsabilidade, tendo como objetivo isolar totalmente a lógica da aplicação do mundo externo. Este isolamento é feito por meio de Portas e Adaptadores (daí o nome Ports and Adapters), onde as Portas são as interfaces que as camadas de baixo nível expõe, e Adaptadores as implementações para as interfaces em questão (inversão de dependência)

![Diagrama](./diagram.jpg)

### Sobre esta POC
A POC foi desenvolvida em Java e consiste em um projeto maven multi-módulos, sendo eles:
#### Hexagonal-poc-domain
+ Módulo responsável pelas regras de negócio da aplicação, contendo os objetos de domínio. Este módulo deve ser independente de frameworks (como spring). 

#### Hexagonal-poc-port
+ Módulo responsável por definir os contratos que serão utilizados pelos demais módulos. Trata-se das interfaces que serão implementadas pelos adapters.  

#### Hexagonal-poc-adapter
+ É neste módulo que ficam as implementações dos adaptadores. Esta camada é responsável por prover as implementações das portas, podendo-se utilizar de diversas tecnologias e frameworks.





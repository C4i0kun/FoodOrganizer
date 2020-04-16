# FoodOrganizer
Esse software tem como objetivo ser a solução do terceiro trabalho de MAC0321 (Laboratório de Programação Orientada a Objetos) de 2020. O objetivo do trabalho é desenvolver um sistema que organize receitas escritas em arquivos ```.txt```, calcule o total de ingredientes necessários para tas receitas e gere um pdf bem organizado com as receitas.  

## Observação importante sobre os testes
Durante o desenvolvimento do programa, foi necessário usar o *Maven* no projeto do *eclipse* para utilizar o *PDFBox* e gerar arquivos PDF. O problema é que isso, de alguma forma, alterou alguma configuração do projeto (E eu não sei resolver isso...). Os testes em JUnit pararam de funcionar caso o *Maven* estivesse habilitado. Por isso, vou deixar abaixo um passo a passo para desabilitar o *Maven* e poder rodar os testes. 
1. Após ter importado o projeto, clique com o botão direito do mouse nele (*FoodOrganizer*) e vá para **Maven** > **Disable Maven Nature**;
2. Acesse ```module-info.java``` e o modifique comentando duas linhas, deixando-o assim: 
```
module foodorganizer {
	requires org.junit.jupiter.api;
	//requires org.apache.pdfbox;
	//requires pdfbox2.layout;
}
```
Aí será possível rodar todos os testes. Isso faz porém, com que o programa rodado pela ```main``` dê um erro, pois a parte do *PDFBox* que precisa do *Maven*, não rodará. E aí seria preciso fazer o inverso do processo acima para voltar ao estado inicial e rodar o programa em si:
1. Descomente as linhas que foram comentadas no passo 2 do tópico acima;
2. Clique com o botão direito do mouse no projeto (FoodOrganizer), vá em **Configure** > **Convert to Maven Project**

Caso tenha ideias do motivo desse erro, entre em contato.

## Organização de Classes
Abaixo, há uma pequena descrição das classes que foram criadas nesse programa e suas principais funcionalidades:

* **Main** -> A classe Main existe somente para iniciar o programa;
* **Controller** -> A classe Controller é o mais alto nível do código, ele faz a integração entre o *RecipesManager* e o *IngredientsManager*. Além disso, é ele que controla a ordem que as coisas vão acontecer, desde a criação dos objetos principais, até a geração do PDF;
* **IngredientsManager** -> Essa classe é instanciada na função ```Controller.run()```. Ela é responsável, como o nome sugere, por administrar os ingredientes. É ele, por exemplo, que instancia os ingredientes-padrão (*StandardIngredients*) lidos no arquivo ```StandardIngredients.txt``` e organiza diferentes ingredientes em uma lista;
* **Ingredient** -> Essa classe, ao ser instanciada, representa um objeto de ingrediente e suas características (nome, quantidade, unidade de contagem);
* **RecipesManager** -> Análoga à *IngredientsManager*, ela gerencia as receitas. É ela, por exemplo, a responsável por ler os arquivos de receitas semanais, instanciar objetos de receitas (veja na classe abaixo) e organizá-las em listas;
* **Recipe** -> Por fim, a classe *Recipe*, ao ser instanciada, representa um objeto receita e suas características (título, ingredientes, passos de preparação).

De forma geral, o *Controller* está um nivel acima do *RecipesManager* e do *IngredientsManager* de modo que ele faz a integração entre ambos (o *RecipesManager* e o *IngredientsManager* não devem, em teoria, interagir entre si). O *RecipesManager* contém uma lista de instâncias de *Recipe*, sendo possível, somente, acessar as receitas por meio dele. De forma análoga, o *IngredientsManager* possui uma lista de instâncias de *Ingredient*, e só é possível acessar os ingredientes que estão sendo contados por meio dele. A ideia dessa hirarquia é organizar o funcionamento das coisas.

Há ainda classes abstratas com funções estáticas úteis que podem ser chamadas em qualquer lugar do código.
* **PDFWriter** -> Possui funções de criação de um PDF. Suas funções são chamdas no fim de ```Controller.run()```;
* **StandarIngredientsReader** -> Possui funções relacionadas à leitura dos ingredientes-padrão. É chamada no *IngredientsManager*.
* **StringManager** -> Possui funções úteis para o manuseio de *Strings* e pode ser usada em qulquer lugar do código.
* **TXTWriter** -> Possui funções relacionadas à escrita de um arquivo ```.txt```, é usado no *Controller* para gerar o arquivo ```IngredientsList.txt```

## Exceções
Uma característica importante desse código é que as exceções devem estar resolvidas no nível abaixo do *Controller*. Assim, o *Controller* não deve tratar exceção nenhuma, e deve admitir que quaisquer funções definidas em níveis mais abaixo estão funcionando corretamente.  
Existem duas exceções personalizadas criadas pelo desenvolvedor:

* **FileNotTXTException** é a exceção que ocorre quando o arquivo de receita que o programa tenta ler não é um arquivo do tipo txt. Quando isso ocorre, a receita será ignorada e o programa passará para a próxima (existe um caso assim colocado de propósito no ```RecipesIndex.txt```)
* **IngredientNotIndexedException** é a exceção que ocorre quando tenta-se adicionar, para contagem, uma quantidade de um ingrediente que não foi indexado anteriormente. Caso o ingrediente não seja um ingrediente-padrão, ele será indexado manualmente.  

As outras exceções referem-se a questões como **IOException** ou **FileNotFoundException** e elas também são resolvidas. Na maioria das vezes, uma exceção somente pula uma parte do código e o programa continua fazendo o resto. Há um caso, porém, em que o programa termina quando ocorre uma exceção, que é quando *RecipesManager* não consegue ler o index de receitas. Se ele não consegue fazê-lo, não há sentido para a continuação do programa.

## Recomendações de execução
* Caso queira adicionar uma receita, utilize o modelo padrão proposto pelas receitas já existentes. Usar uma organização diferente criaria diversas exceções complicadas de serem resolvidas e não houve tempo hábil para tal. Portanto, siga os exemplos dados.
* Lembre-se novamente de fazer os procedimentos citados no início desse README antes de utiizar os testes jUnit nesse projeto.
* **Execute o projeto com UTF-8, ou a leitura dos arquivos falhará!**

## EXTRA: Mesmo ingrediente com várias unidades diferentes
É possível notar que, na lista de ingredientes final, alguns ingredientes possuem mais de uma unidade de contagem. Como por exemplo: ```Manteiga - 1 a gosto, 1 colher(es), 1 xícara(s)```. Mas como isso é feito no código?  
Note que no código, um ingrediente pode ser ordenado de modo que, quando um Array contendo vários ingredientes for ordenado, ingredientes com mesmo nome mas unidades diferentes ficarão juntos. Usando isso, o programa consegue unir as várias diferentes unidades de medida de um ingrediente só, algo que fica bem mais real e útil no dia-a-dia.
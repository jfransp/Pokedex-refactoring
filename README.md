Criei alguns mappers (não sei se estão corretos), iniciei as implementações do Room e do Retrofit.

Estou com algumas dificuldades para entender como instanciar as classes do Room e do Retrofit dentro do
módulo data. Eu preciso fazer alguma coisa de injeção de dependências dentro do módulo? Como eu passo
a database para os dataSources? Também estou tendo alguns problemas em conseguir instanciar a API e
os Daos dentro do data (geralmente eu instancio como singleton dentro dos módulos do Hilt, então
não estou muito acostumado a trabalhar fora desse padrão, mas tenho certeza que é alguma coisa fácil)
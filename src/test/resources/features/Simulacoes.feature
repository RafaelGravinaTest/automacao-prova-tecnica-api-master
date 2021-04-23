#language: pt
#encoding: iso-8859-1
Funcionalidade: Simulacoes

  @ProvaTecnicaAPIMaster @Simulacoes @teste
  Cenario: Consulta todas as simulacoes existentes
    Entao realiza a consulta de todas as simulacoes existentes

  @ProvaTecnicaAPIMaster @Simulacoes
  Cenario: Inserir uma nova simulacao
    Entao realiza inclusao de uma simulacao

  @ProvaTecnicaAPIMaster @Simulacoes
  Cenario: Consulta de simulacoes existentes de um CPF
    Entao realiza a consulta de simulacoes de um CPF

  @ProvaTecnicaAPIMaster @Simulacoes
  Cenario: Alterar uma simulacao pelo CPF
    Entao realiza a alteracao de uma simulacao pelo CPF

  #Cenarios Invalidos
  @ProvaTecnicaAPIMaster @Simulacoes
  Cenario: Inserir uma nova simulacao com payload invalido
    Entao realiza inclusao de uma simulacao com payload invalido

  @ProvaTecnicaAPIMaster @Simulacoes
  Cenario: Inserir uma nova simulacao com CPF duplicado
    Entao realiza inclusao de uma simulacao com CPF duplicado

  @ProvaTecnicaAPIMaster @Simulacoes
  Cenario: Consulta de simulacoes existentes de um CPF invalido
    Entao realiza a consulta de simulacoes de um CPF invalido

  @ProvaTecnicaAPIMaster @Simulacoes
  Cenario: Alterar uma simulacao pelo CPF para um CPF ja existente
    Entao realiza a alteracao de uma simulacao pelo CPF para um CPF ja existente

  @ProvaTecnicaAPIMaster @Simulacoes
  Cenario: Alterar uma simulacao por um CPF inexistente
    Entao realiza a alteracao de uma simulacao por um CPF inexistente

  ##Remocao da simulação criada
  @ProvaTecnicaAPIMaster @Simulacoes
  Cenario: Remover uma simulacao pelo ID
    Entao realiza a remocao de uma simulacao pelo ID
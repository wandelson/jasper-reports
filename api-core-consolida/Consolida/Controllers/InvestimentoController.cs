using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;

namespace Consolida.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class InvestimentoController : ControllerBase
    {
        [HttpGet]
        public IActionResult Get()
        {
            var investimento = new Investimento
            {
                Posicao = new Posicao
                {
                    Saldo = new List<Saldo>()
                }
            };
            investimento.Posicao.Saldo.Add(new Saldo { Nome = "Renda Variavel", Valor = 111 });
            investimento.Posicao.Saldo.Add(new Saldo { Nome = "Renda Fixa", Valor = 555 });
            investimento.Posicao.Saldo.Add(new Saldo { Nome = "CDB POS FIXADO", Valor = 11 });

            return new OkObjectResult(investimento);
        }

        public class Investimento
        {
            public Posicao Posicao { get; set; }
        }

        public class Posicao
        {
            public List<Saldo> Saldo { get; set; }
        }

        public class Saldo
        {
            public string Nome { get; set; }
            public double Valor { get; set; }
        }
    }
}
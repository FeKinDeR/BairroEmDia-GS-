"use strict";
// As quatro entidades correspondem às classes Java e às tabelas SQL.
const moradores=[{id:1,nome:"Ana Exemplo",email:"ana@example.com",bairro:"Centro"},{id:2,nome:"Bruno Exemplo",email:"bruno@example.com",bairro:"Vila Verde"}];
const categorias=[{id:1,nome:"Iluminação"},{id:2,nome:"Vias públicas"},{id:3,nome:"Limpeza"}];
const ocorrencias=[{id:1,titulo:"Lâmpada apagada",descricao:"Poste sem iluminação há três dias.",localizacao:"Rua das Flores, 120",status:"Aberta",moradorId:1,categoriaId:1},{id:2,titulo:"Buraco na via",descricao:"Buraco dificulta a passagem de bicicletas.",localizacao:"Av. Central, 45",status:"Em análise",moradorId:2,categoriaId:2},{id:3,titulo:"Descarte irregular",descricao:"Resíduos removidos no exemplo simulado.",localizacao:"Praça do Sol",status:"Resolvida",moradorId:1,categoriaId:3}];
const atualizacoes=[{id:1,ocorrenciaId:1,data:"2026-11-06",mensagem:"Registro criado pelo morador."},{id:2,ocorrenciaId:2,data:"2026-11-06",mensagem:"Ocorrência em análise na simulação."},{id:3,ocorrenciaId:3,data:"2026-11-06",mensagem:"Resolução registrada na simulação."}];
const $=id=>document.getElementById(id);
function elemento(tag,texto,classe){const el=document.createElement(tag);if(texto!==undefined)el.textContent=texto;if(classe)el.className=classe;return el;}
function cartao(o){
 const card=elemento("article",undefined,"card");
 card.append(elemento("small",categorias.find(c=>c.id===o.categoriaId).nome+" · #"+o.id),elemento("h3",o.titulo),elemento("span",o.status,"badge "+(o.status==="Resolvida"?"resolvida":o.status==="Em análise"?"analise":"")),elemento("p",o.descricao),elemento("p","Local: "+o.localizacao));
 const details=elemento("details"), lista=elemento("ul");details.append(elemento("summary","Ver histórico"));
 atualizacoes.filter(a=>a.ocorrenciaId===o.id).forEach(a=>lista.append(elemento("li",a.data+" — "+a.mensagem)));details.append(lista);card.append(details);return card;
}
if($("total")){ $("total").textContent=ocorrencias.length;$("abertas").textContent=ocorrencias.filter(o=>o.status!=="Resolvida").length;$("resolvidas").textContent=ocorrencias.filter(o=>o.status==="Resolvida").length; }
function filtrar(){
 const busca=$("busca").value.trim().toLocaleLowerCase("pt-BR"),cat=$("categoria-filtro").value,status=$("status-filtro").value;
 const itens=ocorrencias.filter(o=>(o.titulo+" "+o.localizacao).toLocaleLowerCase("pt-BR").includes(busca)&&(!cat||categorias.find(c=>c.id===o.categoriaId).nome===cat)&&(!status||o.status===status));
 $("lista").replaceChildren(...itens.map(cartao));$("contagem").textContent=itens.length+" ocorrência(s) encontrada(s).";
 if(!itens.length)$("lista").append(elemento("p","Nenhum registro corresponde aos filtros."));
}
if($("lista")){["busca","categoria-filtro","status-filtro"].forEach(id=>$(id).addEventListener("input",filtrar));$("limpar").addEventListener("click",()=>{["busca","categoria-filtro","status-filtro"].forEach(id=>$(id).value="");filtrar();});filtrar();}
if($("registro"))$("registro").addEventListener("submit",event=>{
 event.preventDefault();const msg=$("mensagem");msg.className="";
 const minimos={nome:3,bairro:2,titulo:5,localizacao:5,descricao:10};
 for(const campo of $("registro").querySelectorAll("input,select,textarea")){
  campo.value=campo.value.trim();campo.removeAttribute("aria-invalid");
 }
 for(const campo of $("registro").querySelectorAll("input,select,textarea")){
  if(!campo.checkValidity()||(minimos[campo.id]&&campo.value.length<minimos[campo.id])){msg.textContent="Revise o campo "+document.querySelector('label[for="'+campo.id+'"]').textContent+". Preencha um valor válido"+(minimos[campo.id]?" com pelo menos "+minimos[campo.id]+" caracteres.":".");campo.setAttribute("aria-invalid","true");campo.focus();return;}
 }
 let morador=moradores.find(m=>m.email.toLowerCase()===$("email").value.toLowerCase());
 if(!morador){morador={id:Math.max(...moradores.map(m=>m.id))+1,nome:$("nome").value,email:$("email").value,bairro:$("bairro").value};moradores.push(morador);}
 const o={id:Math.max(...ocorrencias.map(o=>o.id))+1,titulo:$("titulo").value,descricao:$("descricao").value,localizacao:$("localizacao").value,status:"Aberta",moradorId:morador.id,categoriaId:Number($("categoria").value)};
 ocorrencias.push(o);atualizacoes.push({id:atualizacoes.length+1,ocorrenciaId:o.id,data:new Date().toLocaleDateString("sv-SE"),mensagem:"Registro criado pelo morador."});
 $("resultado").prepend(cartao(o));$("registro").reset();msg.className="sucesso";msg.textContent="Ocorrência #"+o.id+" registrada na simulação. Confira abaixo. O registro será apagado ao sair ou recarregar.";msg.focus();
});

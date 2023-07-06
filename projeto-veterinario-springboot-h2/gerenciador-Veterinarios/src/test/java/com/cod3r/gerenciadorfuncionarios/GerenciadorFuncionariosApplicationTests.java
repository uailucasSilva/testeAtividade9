package com.cod3r.gerenciadorfuncionarios;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@SpringBootConfiguration
class GerenciadorFuncionariosApplicationTests {

	@Test
	@DisplayName("Testar o titulo da página se a página que está aberta é o título correto")
	public void testarCorreiosCepCorreto() {
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("http://localhost:8080/home");
		assertEquals("Gerenciador de Veterinários", driver.getTitle());

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.titleContains("Gerenciador de Veterinários"));

		driver.quit();
	}

	/*
	 * Parte 1 – Selenium IDE: criar no mínimo 3 scripts (cenário de testes),
	 * sugestões:
	 * • Cadastrar Veterinário
	 */
	@Test
	@DisplayName("Testar o cadastro de veterinarios")
	public void testarCadastroDeVeterinario() {
		String nome = "João";
		String email = "joao@gmail.com";
		String especialidade = "Acupuntura Veterinaria";
		String salario = "2000,00";

		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("http://localhost:8080/home");
		assertEquals("Gerenciador de Veterinários", driver.getTitle());

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.titleContains("Gerenciador de Veterinários"));

		WebElement botaoAdicionar = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/a[1]/button")));

		botaoAdicionar.click();

		WebElement campoNome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='nome']")));

		campoNome.sendKeys(nome);

		WebElement campoEmail = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputEmail")));

		campoEmail.sendKeys(email);

		WebElement campoEspecialidade = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputEspecialidade")));

		campoEspecialidade.sendKeys(especialidade);

		WebElement campoSalario = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputSalario")));

		campoSalario.sendKeys(salario);

		WebElement botaoCadastrar = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/form/div[2]/button")));

		botaoCadastrar.click();

		WebElement tabelaVet = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/table")));

		WebElement campo = tabelaVet.findElement(By.xpath("//td//span[contains(text(), '" + nome + "')]"));

		Assertions.assertNotNull(campo, "O campo com o nome do veterinario não foi encontrado!");

		driver.quit();
	}

	@Test
	@DisplayName("Testar a pesquisa de um veterinario existente")
	public void testarPesquisaDeVeterinario() {
		String nome = "Erica Queiroz Pinto";

		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("http://localhost:8080/home");
		assertEquals("Gerenciador de Veterinários", driver.getTitle());

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.titleContains("Gerenciador de Veterinários"));

		WebElement botaoConsultar = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/a[2]/button")));

		botaoConsultar.click();

		WebElement InputNome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nome")));

		InputNome.sendKeys(nome);

		WebElement botaoConsultarForm = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/form/div[2]/button")));

		botaoConsultarForm.click();

		WebElement tabelaVet = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/table")));

		WebElement campo = tabelaVet.findElement(By.xpath("//td//span[contains(text(), '" + nome +
				"')]"));

		Assertions.assertNotNull(campo, "O campo com o nome do veterinario não foi encontrado!");

		driver.quit();
	}

	@Test
	@DisplayName("Testar excluir um veterinario")
	public void testarExclusaoDeVeterinario() {
		String nome = "João";

		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("http://localhost:8080/home");
		assertEquals("Gerenciador de Veterinários", driver.getTitle());

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.titleContains("Gerenciador de Veterinários"));

		WebElement botaoConsultar = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/a[2]/button")));

		botaoConsultar.click();

		WebElement InputNome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nome")));

		InputNome.sendKeys(nome);

		WebElement botaoConsultarForm = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/form/div[2]/button")));

		botaoConsultarForm.click();

		WebElement tabelaVet = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/table")));

		WebElement campo = tabelaVet.findElement(By.xpath("//td//span[contains(text(), '" + nome + "')]"));

		Assertions.assertNotNull(campo, "O veterinario informado para o delete não foi encontrado!");

		WebElement buttonDelete = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[5]/a[2]")));

		buttonDelete.click();

		WebElement tabelaPosDelete = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/table")));

		List<WebElement> campos = tabelaPosDelete
				.findElements(By.xpath("//td//span[contains(text(), '" + nome + "')]"));

		assertEquals(campos.isEmpty(), true);

		driver.quit();
	}

}

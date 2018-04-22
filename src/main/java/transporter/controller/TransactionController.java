package transporter.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import transporter.entity.Transaction;
import transporter.repository.TransactionRepository;
import transporter.service.WayCalc;

@Controller
public class TransactionController {

	private static final String API_KEY = "AIzaSyDGBhBYu1xbTGMhT-gHUs2evHxmsLdtSsU";

	@Autowired
	private TransactionRepository transactionRepository;

	@GetMapping(path = "/transaction/showall")
	public String showAllTransactions(final Model model) {

		final List<Transaction> transactions = transactionRepository.findAll();

		model.addAttribute("transactions", transactions);
		return "transaction/list";
	}

	@GetMapping(path = "/transaction/userlist")
	public String showTransactionsById(final @RequestParam Long id, final Model model) {

		final List<Transaction> transactions = transactionRepository.findByUserId(id);
		model.addAttribute("transactions", transactions);
		return "transaction/list";
	}

	@GetMapping(path = "/transaction/add")
	public String showAddTransactionForm(final Model model) {
		model.addAttribute("transaction", new Transaction());
		return "transaction/add";
	}

	@PostMapping("/transaction/add")
	public String processAddTransactionForm(@Valid @ModelAttribute final Transaction transaction,
			BindingResult bindingResult) throws IOException {

		if (bindingResult.hasErrors()) {
			return "redirect:add";
		} else {
			double calc = new WayCalc().calculator(transaction.getOrigin(), transaction.getDestination() );
			transaction.setDistance(calc);
			this.transactionRepository.save(transaction);
			return "redirect:/";
		}
	}

	@GetMapping("/distance")
	@ResponseBody
	public double Route(@RequestParam("origin") String origin, @RequestParam("destination") String destination)
			throws IOException {

		OkHttpClient client = new OkHttpClient();

		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origin + "&destinations="
				+ destination + "&key=" + API_KEY;
		Request request = new Request.Builder().url(url).build();

		Response response = client.newCall(request).execute();
		final String json = response.body().string();
		JSONParser parser = new JSONParser();

		JSONObject jsonobj;
		double distance = -1;
		try {
			jsonobj = (JSONObject) parser.parse(json);
			JSONArray dist = (JSONArray) jsonobj.get("rows");
			JSONObject obj2 = (JSONObject) dist.get(0);
			JSONArray disting = (JSONArray) obj2.get("elements");
			JSONObject obj3 = (JSONObject) disting.get(0);
			JSONObject obj4 = (JSONObject) obj3.get("distance");
			JSONObject obj5 = (JSONObject) obj3.get("duration");
			distance = Integer.parseInt(obj4.get("value").toString());
			return distance;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return distance;
	}
}

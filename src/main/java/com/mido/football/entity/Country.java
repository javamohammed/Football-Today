package com.mido.football.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Country {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "iso3")
	private String iso3;
	
	@Column(name = "iso2")
	private String iso2;
	
	@Column(name = "phonecode")
	private String phonecode;
	
	@Column(name = "capital")
	private String capital;
	
	@Column(name = "currency")
	private String currency;
	
	@Column(name = "native_lang")
	private String nativeLang;
	
	@Column(name = "emoji")
	private String emoji;
	
	@Column(name = "emojiU")
	private String emojiU;
	
	@Column(name = "created_at")
	private String created_at;
	
	@Column(name = "updated_at")
	private String updated_at;
	
	@Column(name = "flag")
	private String flag;
	
	@Column(name = "wikiDataId")
	private String wikiDataId;
	
	@OneToMany(fetch = FetchType.LAZY , cascade =CascadeType.ALL)
	@JoinColumn(name = "country_id")
	public List<State> states ;
	
	
	public Country() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIso3() {
		return iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}

	public String getIso2() {
		return iso2;
	}

	public void setIso2(String iso2) {
		this.iso2 = iso2;
	}

	public String getPhonecode() {
		return phonecode;
	}

	public void setPhonecode(String phonecode) {
		this.phonecode = phonecode;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getNativeLang() {
		return nativeLang;
	}

	public void setNativeLang(String nativeLang) {
		this.nativeLang = nativeLang;
	}

	public String getEmoji() {
		return emoji;
	}

	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}

	public String getEmojiU() {
		return emojiU;
	}

	public void setEmojiU(String emojiU) {
		this.emojiU = emojiU;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getWikiDataId() {
		return wikiDataId;
	}

	public void setWikiDataId(String wikiDataId) {
		this.wikiDataId = wikiDataId;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", iso3=" + iso3 + ", iso2=" + iso2 + ", phonecode=" + phonecode
				+ ", capital=" + capital + ", currency=" + currency + ", nativeLang=" + nativeLang + ", emoji=" + emoji
				+ ", emojiU=" + emojiU + ", created_at=" + created_at + ", updated_at=" + updated_at + ", flag=" + flag
				+ ", wikiDataId=" + wikiDataId+ ", state=" + states.toString()+ "]";
	}
	
	public void setStates(List<State> states) {
		this.states = states;
	}
	
	public void add(State state) {
		if(states == null) {
			this.states = new ArrayList<State>();
		}
		this.states.add(state);
		
	}

	public List<State> getStates() {
		return states;
	}
	
	
	
	
}

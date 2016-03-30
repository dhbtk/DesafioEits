require 'xmlsimple'
require 'rubystats'
require 'active_support/core_ext/numeric/time'
require 'active_support/core_ext/date/calculations'

file = XmlSimple.xml_in('stockontrol/src/test/resources/sampleProducts.xml', 'KeyAttr' => 'name')
time_format = "%Y-%m-%d"
user_id = '1'


puts %(<?xml version="1.0" encoding="UTF-8" ?>)
puts %(<dataset>)
id = 1
file['products'].each do |name, data|
	seq = 1
	gen = Rubystats::NormalDistribution.new(90, 20)
	gen.rng((25..55).sort_by{rand}[0]).map(&:to_i).each do |qtd|
		identifier = "%.4s%.3d" % [(name.hash % 65536).to_s(16).upcase, seq]
		base_date = Time.now + ((rand - 1)*60).days
		manufactured_at = base_date - (rand*30).days
		expires_at = base_date + (rand*60).days
		puts %(<batches id="#{id}" product_id="#{data['id']}" user_id="#{user_id}" created_at="#{Time.now.strftime time_format}" updated_at="#{Time.now.strftime time_format}" manufactured_at="#{manufactured_at.strftime time_format}" expires_at="#{expires_at.strftime time_format}" identifier="#{identifier}" quantity="#{qtd}" />)
		seq += 1
		id += 1
	end
end
puts %(</dataset>)

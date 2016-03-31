require 'xmlsimple'

ARGV.each do |f|
	file = XmlSimple.xml_in(f)

	table_name = file.keys[0]

	file[table_name].each do |i|
		data = i.map do |k, v|
			if k[/_id$/] || k == 'quantity' || k == 'id' || k == 'price' || k == 'profile'
				v = v
			else
				v = "'#{v}'"
			end
			[k, v]
		end.to_h
		puts %(INSERT INTO #{table_name} (#{data.keys.join(', ')}) VALUES (#{data.values.join(', ')});)
	end
end

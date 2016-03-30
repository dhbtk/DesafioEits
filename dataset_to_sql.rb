require 'xmlsimple'

file = XmlSimple.xml_in(ARGV[0])

table_name = file.keys[0]

file[table_name].each do |i|
	data = i.map do |k, v|
		if k[/_id$/] || k == 'quantity' || k == 'id'
			v = v
		else
			v = "'#{v}'"
		end
		[k, v]
	end.to_h
	puts %(INSERT INTO #{table_name} (#{data.keys.join(', ')}) VALUES (#{data.values.join(', ')});)
end

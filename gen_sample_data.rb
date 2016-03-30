user_id = '1'
cat_id = ARGV.shift
price  = ARGV.shift

time_format = "%Y-%m-%d %H:%M:%S.%L"

i = 1
ARGV.each do |p|
	puts %(<products created_at="#{Time.now.strftime(time_format)}" updated_at="#{Time.now.strftime(time_format)}" id="#{i}" user_id="#{user_id}" category_id="#{cat_id}" name="#{p}" price="#{price}" />)
end
